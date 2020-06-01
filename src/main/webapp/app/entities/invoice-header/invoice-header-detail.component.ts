import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';

@Component({
  selector: 'jhi-invoice-header-detail',
  templateUrl: './invoice-header-detail.component.html',
})
export class InvoiceHeaderDetailComponent implements OnInit {
  invoiceHeader: IInvoiceHeader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceHeader }) => (this.invoiceHeader = invoiceHeader));
  }

  previousState(): void {
    window.history.back();
  }
}
