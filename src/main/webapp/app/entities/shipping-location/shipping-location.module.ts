import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ShippingLocationComponent } from './shipping-location.component';
import { ShippingLocationDetailComponent } from './shipping-location-detail.component';
import { ShippingLocationUpdateComponent } from './shipping-location-update.component';
import { ShippingLocationDeleteDialogComponent } from './shipping-location-delete-dialog.component';
import { shippingLocationRoute } from './shipping-location.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(shippingLocationRoute)],
  declarations: [
    ShippingLocationComponent,
    ShippingLocationDetailComponent,
    ShippingLocationUpdateComponent,
    ShippingLocationDeleteDialogComponent,
  ],
  entryComponents: [ShippingLocationDeleteDialogComponent],
})
export class AppShippingLocationModule {}
