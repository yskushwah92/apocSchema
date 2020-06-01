import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGRNDetails, GRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from './grn-details.service';

@Component({
  selector: 'jhi-grn-details-update',
  templateUrl: './grn-details-update.component.html',
})
export class GRNDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    grnNumber: [],
    grnLineNumber: [],
    grnQuantity: [],
    grnType: [],
    deliveryNote: [],
    isOpen: [],
  });

  constructor(protected gRNDetailsService: GRNDetailsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gRNDetails }) => {
      this.updateForm(gRNDetails);
    });
  }

  updateForm(gRNDetails: IGRNDetails): void {
    this.editForm.patchValue({
      id: gRNDetails.id,
      grnNumber: gRNDetails.grnNumber,
      grnLineNumber: gRNDetails.grnLineNumber,
      grnQuantity: gRNDetails.grnQuantity,
      grnType: gRNDetails.grnType,
      deliveryNote: gRNDetails.deliveryNote,
      isOpen: gRNDetails.isOpen,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gRNDetails = this.createFromForm();
    if (gRNDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.gRNDetailsService.update(gRNDetails));
    } else {
      this.subscribeToSaveResponse(this.gRNDetailsService.create(gRNDetails));
    }
  }

  private createFromForm(): IGRNDetails {
    return {
      ...new GRNDetails(),
      id: this.editForm.get(['id'])!.value,
      grnNumber: this.editForm.get(['grnNumber'])!.value,
      grnLineNumber: this.editForm.get(['grnLineNumber'])!.value,
      grnQuantity: this.editForm.get(['grnQuantity'])!.value,
      grnType: this.editForm.get(['grnType'])!.value,
      deliveryNote: this.editForm.get(['deliveryNote'])!.value,
      isOpen: this.editForm.get(['isOpen'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGRNDetails>>): void {
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
