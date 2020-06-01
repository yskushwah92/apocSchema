import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { PurchaseOrderLineComponent } from './purchase-order-line.component';
import { PurchaseOrderLineDetailComponent } from './purchase-order-line-detail.component';
import { PurchaseOrderLineUpdateComponent } from './purchase-order-line-update.component';
import { PurchaseOrderLineDeleteDialogComponent } from './purchase-order-line-delete-dialog.component';
import { purchaseOrderLineRoute } from './purchase-order-line.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(purchaseOrderLineRoute)],
  declarations: [
    PurchaseOrderLineComponent,
    PurchaseOrderLineDetailComponent,
    PurchaseOrderLineUpdateComponent,
    PurchaseOrderLineDeleteDialogComponent,
  ],
  entryComponents: [PurchaseOrderLineDeleteDialogComponent],
})
export class AppPurchaseOrderLineModule {}
