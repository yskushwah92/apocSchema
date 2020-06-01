import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';

@Component({
  selector: 'jhi-purchase-order-header-detail',
  templateUrl: './purchase-order-header-detail.component.html',
})
export class PurchaseOrderHeaderDetailComponent implements OnInit {
  purchaseOrderHeader: IPurchaseOrderHeader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ purchaseOrderHeader }) => (this.purchaseOrderHeader = purchaseOrderHeader));
  }

  previousState(): void {
    window.history.back();
  }
}
