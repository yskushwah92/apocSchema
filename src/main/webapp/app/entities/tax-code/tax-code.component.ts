import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITaxCode } from 'app/shared/model/tax-code.model';
import { TaxCodeService } from './tax-code.service';
import { TaxCodeDeleteDialogComponent } from './tax-code-delete-dialog.component';

@Component({
  selector: 'jhi-tax-code',
  templateUrl: './tax-code.component.html',
})
export class TaxCodeComponent implements OnInit, OnDestroy {
  taxCodes?: ITaxCode[];
  eventSubscriber?: Subscription;

  constructor(protected taxCodeService: TaxCodeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.taxCodeService.query().subscribe((res: HttpResponse<ITaxCode[]>) => (this.taxCodes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTaxCodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITaxCode): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTaxCodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('taxCodeListModification', () => this.loadAll());
  }

  delete(taxCode: ITaxCode): void {
    const modalRef = this.modalService.open(TaxCodeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.taxCode = taxCode;
  }
}
