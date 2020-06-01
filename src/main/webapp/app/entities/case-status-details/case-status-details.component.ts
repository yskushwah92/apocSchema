import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICaseStatusDetails } from 'app/shared/model/case-status-details.model';
import { CaseStatusDetailsService } from './case-status-details.service';
import { CaseStatusDetailsDeleteDialogComponent } from './case-status-details-delete-dialog.component';

@Component({
  selector: 'jhi-case-status-details',
  templateUrl: './case-status-details.component.html',
})
export class CaseStatusDetailsComponent implements OnInit, OnDestroy {
  caseStatusDetails?: ICaseStatusDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected caseStatusDetailsService: CaseStatusDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.caseStatusDetailsService.query().subscribe((res: HttpResponse<ICaseStatusDetails[]>) => (this.caseStatusDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCaseStatusDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICaseStatusDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCaseStatusDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('caseStatusDetailsListModification', () => this.loadAll());
  }

  delete(caseStatusDetails: ICaseStatusDetails): void {
    const modalRef = this.modalService.open(CaseStatusDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.caseStatusDetails = caseStatusDetails;
  }
}
