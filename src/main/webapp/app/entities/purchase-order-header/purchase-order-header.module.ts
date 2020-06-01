import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { PurchaseOrderHeaderComponent } from './purchase-order-header.component';
import { PurchaseOrderHeaderDetailComponent } from './purchase-order-header-detail.component';
import { PurchaseOrderHeaderUpdateComponent } from './purchase-order-header-update.component';
import { PurchaseOrderHeaderDeleteDialogComponent } from './purchase-order-header-delete-dialog.component';
import { purchaseOrderHeaderRoute } from './purchase-order-header.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(purchaseOrderHeaderRoute)],
  declarations: [
    PurchaseOrderHeaderComponent,
    PurchaseOrderHeaderDetailComponent,
    PurchaseOrderHeaderUpdateComponent,
    PurchaseOrderHeaderDeleteDialogComponent,
  ],
  entryComponents: [PurchaseOrderHeaderDeleteDialogComponent],
})
export class AppPurchaseOrderHeaderModule {}
