import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IShippingLocation, ShippingLocation } from 'app/shared/model/shipping-location.model';
import { ShippingLocationService } from './shipping-location.service';

@Component({
  selector: 'jhi-shipping-location-update',
  templateUrl: './shipping-location-update.component.html',
})
export class ShippingLocationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    locationCode: [],
    description: [],
    address: [],
    country: [],
    postalCode: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(
    protected shippingLocationService: ShippingLocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingLocation }) => {
      if (!shippingLocation.id) {
        const today = moment().startOf('day');
        shippingLocation.createdAt = today;
      }

      this.updateForm(shippingLocation);
    });
  }

  updateForm(shippingLocation: IShippingLocation): void {
    this.editForm.patchValue({
      id: shippingLocation.id,
      name: shippingLocation.name,
      locationCode: shippingLocation.locationCode,
      description: shippingLocation.description,
      address: shippingLocation.address,
      country: shippingLocation.country,
      postalCode: shippingLocation.postalCode,
      createdAt: shippingLocation.createdAt ? shippingLocation.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: shippingLocation.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shippingLocation = this.createFromForm();
    if (shippingLocation.id !== undefined) {
      this.subscribeToSaveResponse(this.shippingLocationService.update(shippingLocation));
    } else {
      this.subscribeToSaveResponse(this.shippingLocationService.create(shippingLocation));
    }
  }

  private createFromForm(): IShippingLocation {
    return {
      ...new ShippingLocation(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      locationCode: this.editForm.get(['locationCode'])!.value,
      description: this.editForm.get(['description'])!.value,
      address: this.editForm.get(['address'])!.value,
      country: this.editForm.get(['country'])!.value,
      postalCode: this.editForm.get(['postalCode'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingLocation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
