import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShippingLocation } from 'app/shared/model/shipping-location.model';

@Component({
  selector: 'jhi-shipping-location-detail',
  templateUrl: './shipping-location-detail.component.html',
})
export class ShippingLocationDetailComponent implements OnInit {
  shippingLocation: IShippingLocation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingLocation }) => (this.shippingLocation = shippingLocation));
  }

  previousState(): void {
    window.history.back();
  }
}
