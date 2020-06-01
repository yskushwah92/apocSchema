import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { VendorPaymentAccountDetailsComponent } from './vendor-payment-account-details.component';
import { VendorPaymentAccountDetailsDetailComponent } from './vendor-payment-account-details-detail.component';
import { VendorPaymentAccountDetailsUpdateComponent } from './vendor-payment-account-details-update.component';
import { VendorPaymentAccountDetailsDeleteDialogComponent } from './vendor-payment-account-details-delete-dialog.component';
import { vendorPaymentAccountDetailsRoute } from './vendor-payment-account-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(vendorPaymentAccountDetailsRoute)],
  declarations: [
    VendorPaymentAccountDetailsComponent,
    VendorPaymentAccountDetailsDetailComponent,
    VendorPaymentAccountDetailsUpdateComponent,
    VendorPaymentAccountDetailsDeleteDialogComponent,
  ],
  entryComponents: [VendorPaymentAccountDetailsDeleteDialogComponent],
})
export class AppVendorPaymentAccountDetailsModule {}
