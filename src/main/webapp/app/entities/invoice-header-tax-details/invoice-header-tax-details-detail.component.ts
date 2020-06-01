import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';

@Component({
  selector: 'jhi-invoice-header-tax-details-detail',
  templateUrl: './invoice-header-tax-details-detail.component.html',
})
export class InvoiceHeaderTaxDetailsDetailComponent implements OnInit {
  invoiceHeaderTaxDetails: IInvoiceHeaderTaxDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceHeaderTaxDetails }) => (this.invoiceHeaderTaxDetails = invoiceHeaderTaxDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
