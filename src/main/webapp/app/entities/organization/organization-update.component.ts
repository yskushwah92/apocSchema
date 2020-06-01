import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { OrganizationService } from './organization.service';
import { IContactDetails } from 'app/shared/model/contact-details.model';
import { ContactDetailsService } from 'app/entities/contact-details/contact-details.service';

@Component({
  selector: 'jhi-organization-update',
  templateUrl: './organization-update.component.html',
})
export class OrganizationUpdateComponent implements OnInit {
  isSaving = false;
  contactdetails: IContactDetails[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    organizationCode: [],
    orgName: [],
    countryKey: [],
    financeSystem: [],
    vatRegistrationNo: [],
    currency: [],
    language: [],
    createdAt: [],
    createdBy: [],
    contactDetails: [],
  });

  constructor(
    protected organizationService: OrganizationService,
    protected contactDetailsService: ContactDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organization }) => {
      if (!organization.id) {
        const today = moment().startOf('day');
        organization.createdAt = today;
      }

      this.updateForm(organization);

      this.contactDetailsService
        .query({ filter: 'organization-is-null' })
        .pipe(
          map((res: HttpResponse<IContactDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IContactDetails[]) => {
          if (!organization.contactDetails || !organization.contactDetails.id) {
            this.contactdetails = resBody;
          } else {
            this.contactDetailsService
              .find(organization.contactDetails.id)
              .pipe(
                map((subRes: HttpResponse<IContactDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IContactDetails[]) => (this.contactdetails = concatRes));
          }
        });
    });
  }

  updateForm(organization: IOrganization): void {
    this.editForm.patchValue({
      id: organization.id,
      name: organization.name,
      organizationCode: organization.organizationCode,
      orgName: organization.orgName,
      countryKey: organization.countryKey,
      financeSystem: organization.financeSystem,
      vatRegistrationNo: organization.vatRegistrationNo,
      currency: organization.currency,
      language: organization.language,
      createdAt: organization.createdAt ? organization.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: organization.createdBy,
      contactDetails: organization.contactDetails,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organization = this.createFromForm();
    if (organization.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationService.update(organization));
    } else {
      this.subscribeToSaveResponse(this.organizationService.create(organization));
    }
  }

  private createFromForm(): IOrganization {
    return {
      ...new Organization(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      organizationCode: this.editForm.get(['organizationCode'])!.value,
      orgName: this.editForm.get(['orgName'])!.value,
      countryKey: this.editForm.get(['countryKey'])!.value,
      financeSystem: this.editForm.get(['financeSystem'])!.value,
      vatRegistrationNo: this.editForm.get(['vatRegistrationNo'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      language: this.editForm.get(['language'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      contactDetails: this.editForm.get(['contactDetails'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganization>>): void {
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

  trackById(index: number, item: IContactDetails): any {
    return item.id;
  }
}
