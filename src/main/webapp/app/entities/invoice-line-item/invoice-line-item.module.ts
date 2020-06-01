import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { InvoiceLineItemComponent } from './invoice-line-item.component';
import { InvoiceLineItemDetailComponent } from './invoice-line-item-detail.component';
import { InvoiceLineItemUpdateComponent } from './invoice-line-item-update.component';
import { InvoiceLineItemDeleteDialogComponent } from './invoice-line-item-delete-dialog.component';
import { invoiceLineItemRoute } from './invoice-line-item.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(invoiceLineItemRoute)],
  declarations: [
    InvoiceLineItemComponent,
    InvoiceLineItemDetailComponent,
    InvoiceLineItemUpdateComponent,
    InvoiceLineItemDeleteDialogComponent,
  ],
  entryComponents: [InvoiceLineItemDeleteDialogComponent],
})
export class AppInvoiceLineItemModule {}
