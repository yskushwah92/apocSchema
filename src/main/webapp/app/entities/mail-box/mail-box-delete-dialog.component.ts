import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMailBox } from 'app/shared/model/mail-box.model';
import { MailBoxService } from './mail-box.service';

@Component({
  templateUrl: './mail-box-delete-dialog.component.html',
})
export class MailBoxDeleteDialogComponent {
  mailBox?: IMailBox;

  constructor(protected mailBoxService: MailBoxService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mailBoxService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mailBoxListModification');
      this.activeModal.close();
    });
  }
}
