import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from './gl-account-details.service';
import { GLAccountDetailsDeleteDialogComponent } from './gl-account-details-delete-dialog.component';

@Component({
  selector: 'jhi-gl-account-details',
  templateUrl: './gl-account-details.component.html',
})
export class GLAccountDetailsComponent implements OnInit, OnDestroy {
  gLAccountDetails?: IGLAccountDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected gLAccountDetailsService: GLAccountDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.gLAccountDetailsService.query().subscribe((res: HttpResponse<IGLAccountDetails[]>) => (this.gLAccountDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGLAccountDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGLAccountDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGLAccountDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('gLAccountDetailsListModification', () => this.loadAll());
  }

  delete(gLAccountDetails: IGLAccountDetails): void {
    const modalRef = this.modalService.open(GLAccountDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gLAccountDetails = gLAccountDetails;
  }
}
