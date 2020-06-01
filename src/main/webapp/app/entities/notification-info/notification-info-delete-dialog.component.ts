import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotificationInfo } from 'app/shared/model/notification-info.model';
import { NotificationInfoService } from './notification-info.service';

@Component({
  templateUrl: './notification-info-delete-dialog.component.html',
})
export class NotificationInfoDeleteDialogComponent {
  notificationInfo?: INotificationInfo;

  constructor(
    protected notificationInfoService: NotificationInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notificationInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notificationInfoListModification');
      this.activeModal.close();
    });
  }
}
