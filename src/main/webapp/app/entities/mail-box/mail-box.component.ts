import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMailBox } from 'app/shared/model/mail-box.model';
import { MailBoxService } from './mail-box.service';
import { MailBoxDeleteDialogComponent } from './mail-box-delete-dialog.component';

@Component({
  selector: 'jhi-mail-box',
  templateUrl: './mail-box.component.html',
})
export class MailBoxComponent implements OnInit, OnDestroy {
  mailBoxes?: IMailBox[];
  eventSubscriber?: Subscription;

  constructor(protected mailBoxService: MailBoxService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mailBoxService.query().subscribe((res: HttpResponse<IMailBox[]>) => (this.mailBoxes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMailBoxes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMailBox): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMailBoxes(): void {
    this.eventSubscriber = this.eventManager.subscribe('mailBoxListModification', () => this.loadAll());
  }

  delete(mailBox: IMailBox): void {
    const modalRef = this.modalService.open(MailBoxDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mailBox = mailBox;
  }
}
