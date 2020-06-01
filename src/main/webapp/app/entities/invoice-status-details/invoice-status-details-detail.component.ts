import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';

@Component({
  selector: 'jhi-invoice-status-details-detail',
  templateUrl: './invoice-status-details-detail.component.html',
})
export class InvoiceStatusDetailsDetailComponent implements OnInit {
  invoiceStatusDetails: IInvoiceStatusDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceStatusDetails }) => (this.invoiceStatusDetails = invoiceStatusDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
