import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from './grn-details.service';
import { GRNDetailsDeleteDialogComponent } from './grn-details-delete-dialog.component';

@Component({
  selector: 'jhi-grn-details',
  templateUrl: './grn-details.component.html',
})
export class GRNDetailsComponent implements OnInit, OnDestroy {
  gRNDetails?: IGRNDetails[];
  eventSubscriber?: Subscription;

  constructor(protected gRNDetailsService: GRNDetailsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.gRNDetailsService.query().subscribe((res: HttpResponse<IGRNDetails[]>) => (this.gRNDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGRNDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGRNDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGRNDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('gRNDetailsListModification', () => this.loadAll());
  }

  delete(gRNDetails: IGRNDetails): void {
    const modalRef = this.modalService.open(GRNDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gRNDetails = gRNDetails;
  }
}
