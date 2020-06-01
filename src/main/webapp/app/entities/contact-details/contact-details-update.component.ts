import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IContactDetails, ContactDetails } from 'app/shared/model/contact-details.model';
import { ContactDetailsService } from './contact-details.service';

@Component({
  selector: 'jhi-contact-details-update',
  templateUrl: './contact-details-update.component.html',
})
export class ContactDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    street: [],
    zipCode: [],
    city: [],
    district: [],
    poBox: [],
    poBoxCode: [],
    country: [],
    telephoneNo: [],
    faxNumber: [],
    email: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(protected contactDetailsService: ContactDetailsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactDetails }) => {
      if (!contactDetails.id) {
        const today = moment().startOf('day');
        contactDetails.createdAt = today;
      }

      this.updateForm(contactDetails);
    });
  }

  updateForm(contactDetails: IContactDetails): void {
    this.editForm.patchValue({
      id: contactDetails.id,
      street: contactDetails.street,
      zipCode: contactDetails.zipCode,
      city: contactDetails.city,
      district: contactDetails.district,
      poBox: contactDetails.poBox,
      poBoxCode: contactDetails.poBoxCode,
      country: contactDetails.country,
      telephoneNo: contactDetails.telephoneNo,
      faxNumber: contactDetails.faxNumber,
      email: contactDetails.email,
      createdAt: contactDetails.createdAt ? contactDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: contactDetails.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contactDetails = this.createFromForm();
    if (contactDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.contactDetailsService.update(contactDetails));
    } else {
      this.subscribeToSaveResponse(this.contactDetailsService.create(contactDetails));
    }
  }

  private createFromForm(): IContactDetails {
    return {
      ...new ContactDetails(),
      id: this.editForm.get(['id'])!.value,
      street: this.editForm.get(['street'])!.value,
      zipCode: this.editForm.get(['zipCode'])!.value,
      city: this.editForm.get(['city'])!.value,
      district: this.editForm.get(['district'])!.value,
      poBox: this.editForm.get(['poBox'])!.value,
      poBoxCode: this.editForm.get(['poBoxCode'])!.value,
      country: this.editForm.get(['country'])!.value,
      telephoneNo: this.editForm.get(['telephoneNo'])!.value,
      faxNumber: this.editForm.get(['faxNumber'])!.value,
      email: this.editForm.get(['email'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContactDetails>>): void {
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
