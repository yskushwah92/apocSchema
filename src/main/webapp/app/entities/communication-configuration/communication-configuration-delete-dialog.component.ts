import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommunicationConfiguration } from 'app/shared/model/communication-configuration.model';
import { CommunicationConfigurationService } from './communication-configuration.service';

@Component({
  templateUrl: './communication-configuration-delete-dialog.component.html',
})
export class CommunicationConfigurationDeleteDialogComponent {
  communicationConfiguration?: ICommunicationConfiguration;

  constructor(
    protected communicationConfigurationService: CommunicationConfigurationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.communicationConfigurationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('communicationConfigurationListModification');
      this.activeModal.close();
    });
  }
}
