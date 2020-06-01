import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { VendorComponent } from './vendor.component';
import { VendorDetailComponent } from './vendor-detail.component';
import { VendorUpdateComponent } from './vendor-update.component';
import { VendorDeleteDialogComponent } from './vendor-delete-dialog.component';
import { vendorRoute } from './vendor.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(vendorRoute)],
  declarations: [VendorComponent, VendorDetailComponent, VendorUpdateComponent, VendorDeleteDialogComponent],
  entryComponents: [VendorDeleteDialogComponent],
})
export class AppVendorModule {}
