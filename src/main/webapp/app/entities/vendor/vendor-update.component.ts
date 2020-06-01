import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVendor, Vendor } from 'app/shared/model/vendor.model';
import { VendorService } from './vendor.service';
import { IContactDetails } from 'app/shared/model/contact-details.model';
import { ContactDetailsService } from 'app/entities/contact-details/contact-details.service';
import { IOrganization } from 'app/shared/model/organization.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from 'app/entities/gl-account-details/gl-account-details.service';

type SelectableEntity = IContactDetails | IOrganization | IGLAccountDetails;

@Component({
  selector: 'jhi-vendor-update',
  templateUrl: './vendor-update.component.html',
})
export class VendorUpdateComponent implements OnInit {
  isSaving = false;
  contactdetails: IContactDetails[] = [];
  organizations: IOrganization[] = [];
  glaccountdetails: IGLAccountDetails[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    displayName: [],
    vendorNumber: [],
    paymentTerms: [],
    type: [],
    iln: [],
    taxIdNO: [],
    gstRegistrationNumber: [],
    gstStatus: [],
    vatIdNo: [],
    clerkId: [],
    status: [],
    vatRegistrationNumber: [],
    prefferredModeOfCommunication: [],
    pointOfContact: [],
    preferredModeOfPayment: [],
    createdAt: [],
    createdBy: [],
    contactDetails: [],
    organization: [],
    gLAccountDetails: [],
  });

  constructor(
    protected vendorService: VendorService,
    protected contactDetailsService: ContactDetailsService,
    protected organizationService: OrganizationService,
    protected gLAccountDetailsService: GLAccountDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vendor }) => {
      if (!vendor.id) {
        const today = moment().startOf('day');
        vendor.createdAt = today;
      }

      this.updateForm(vendor);

      this.contactDetailsService
        .query({ filter: 'vendor-is-null' })
        .pipe(
          map((res: HttpResponse<IContactDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IContactDetails[]) => {
          if (!vendor.contactDetails || !vendor.contactDetails.id) {
            this.contactdetails = resBody;
          } else {
            this.contactDetailsService
              .find(vendor.contactDetails.id)
              .pipe(
                map((subRes: HttpResponse<IContactDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IContactDetails[]) => (this.contactdetails = concatRes));
          }
        });

      this.organizationService
        .query({ filter: 'vendor-is-null' })
        .pipe(
          map((res: HttpResponse<IOrganization[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IOrganization[]) => {
          if (!vendor.organization || !vendor.organization.id) {
            this.organizations = resBody;
          } else {
            this.organizationService
              .find(vendor.organization.id)
              .pipe(
                map((subRes: HttpResponse<IOrganization>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IOrganization[]) => (this.organizations = concatRes));
          }
        });

      this.gLAccountDetailsService
        .query({ filter: 'vendor-is-null' })
        .pipe(
          map((res: HttpResponse<IGLAccountDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGLAccountDetails[]) => {
          if (!vendor.gLAccountDetails || !vendor.gLAccountDetails.id) {
            this.glaccountdetails = resBody;
          } else {
            this.gLAccountDetailsService
              .find(vendor.gLAccountDetails.id)
              .pipe(
                map((subRes: HttpResponse<IGLAccountDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGLAccountDetails[]) => (this.glaccountdetails = concatRes));
          }
        });
    });
  }

  updateForm(vendor: IVendor): void {
    this.editForm.patchValue({
      id: vendor.id,
      name: vendor.name,
      displayName: vendor.displayName,
      vendorNumber: vendor.vendorNumber,
      paymentTerms: vendor.paymentTerms,
      type: vendor.type,
      iln: vendor.iln,
      taxIdNO: vendor.taxIdNO,
      gstRegistrationNumber: vendor.gstRegistrationNumber,
      gstStatus: vendor.gstStatus,
      vatIdNo: vendor.vatIdNo,
      clerkId: vendor.clerkId,
      status: vendor.status,
      vatRegistrationNumber: vendor.vatRegistrationNumber,
      prefferredModeOfCommunication: vendor.prefferredModeOfCommunication,
      pointOfContact: vendor.pointOfContact,
      preferredModeOfPayment: vendor.preferredModeOfPayment,
      createdAt: vendor.createdAt ? vendor.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: vendor.createdBy,
      contactDetails: vendor.contactDetails,
      organization: vendor.organization,
      gLAccountDetails: vendor.gLAccountDetails,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vendor = this.createFromForm();
    if (vendor.id !== undefined) {
      this.subscribeToSaveResponse(this.vendorService.update(vendor));
    } else {
      this.subscribeToSaveResponse(this.vendorService.create(vendor));
    }
  }

  private createFromForm(): IVendor {
    return {
      ...new Vendor(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      displayName: this.editForm.get(['displayName'])!.value,
      vendorNumber: this.editForm.get(['vendorNumber'])!.value,
      paymentTerms: this.editForm.get(['paymentTerms'])!.value,
      type: this.editForm.get(['type'])!.value,
      iln: this.editForm.get(['iln'])!.value,
      taxIdNO: this.editForm.get(['taxIdNO'])!.value,
      gstRegistrationNumber: this.editForm.get(['gstRegistrationNumber'])!.value,
      gstStatus: this.editForm.get(['gstStatus'])!.value,
      vatIdNo: this.editForm.get(['vatIdNo'])!.value,
      clerkId: this.editForm.get(['clerkId'])!.value,
      status: this.editForm.get(['status'])!.value,
      vatRegistrationNumber: this.editForm.get(['vatRegistrationNumber'])!.value,
      prefferredModeOfCommunication: this.editForm.get(['prefferredModeOfCommunication'])!.value,
      pointOfContact: this.editForm.get(['pointOfContact'])!.value,
      preferredModeOfPayment: this.editForm.get(['preferredModeOfPayment'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      contactDetails: this.editForm.get(['contactDetails'])!.value,
      organization: this.editForm.get(['organization'])!.value,
      gLAccountDetails: this.editForm.get(['gLAccountDetails'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendor>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
