import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotificationInfo } from 'app/shared/model/notification-info.model';
import { NotificationInfoService } from './notification-info.service';
import { NotificationInfoDeleteDialogComponent } from './notification-info-delete-dialog.component';

@Component({
  selector: 'jhi-notification-info',
  templateUrl: './notification-info.component.html',
})
export class NotificationInfoComponent implements OnInit, OnDestroy {
  notificationInfos?: INotificationInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected notificationInfoService: NotificationInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.notificationInfoService.query().subscribe((res: HttpResponse<INotificationInfo[]>) => (this.notificationInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotificationInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotificationInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotificationInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('notificationInfoListModification', () => this.loadAll());
  }

  delete(notificationInfo: INotificationInfo): void {
    const modalRef = this.modalService.open(NotificationInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notificationInfo = notificationInfo;
  }
}
