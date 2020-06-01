import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommunicationConfiguration } from 'app/shared/model/communication-configuration.model';
import { CommunicationConfigurationService } from './communication-configuration.service';
import { CommunicationConfigurationDeleteDialogComponent } from './communication-configuration-delete-dialog.component';

@Component({
  selector: 'jhi-communication-configuration',
  templateUrl: './communication-configuration.component.html',
})
export class CommunicationConfigurationComponent implements OnInit, OnDestroy {
  communicationConfigurations?: ICommunicationConfiguration[];
  eventSubscriber?: Subscription;

  constructor(
    protected communicationConfigurationService: CommunicationConfigurationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.communicationConfigurationService
      .query()
      .subscribe((res: HttpResponse<ICommunicationConfiguration[]>) => (this.communicationConfigurations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCommunicationConfigurations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICommunicationConfiguration): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCommunicationConfigurations(): void {
    this.eventSubscriber = this.eventManager.subscribe('communicationConfigurationListModification', () => this.loadAll());
  }

  delete(communicationConfiguration: ICommunicationConfiguration): void {
    const modalRef = this.modalService.open(CommunicationConfigurationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.communicationConfiguration = communicationConfiguration;
  }
}
