import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { InvoiceStatusDetailsComponent } from './invoice-status-details.component';
import { InvoiceStatusDetailsDetailComponent } from './invoice-status-details-detail.component';
import { InvoiceStatusDetailsUpdateComponent } from './invoice-status-details-update.component';
import { InvoiceStatusDetailsDeleteDialogComponent } from './invoice-status-details-delete-dialog.component';
import { invoiceStatusDetailsRoute } from './invoice-status-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(invoiceStatusDetailsRoute)],
  declarations: [
    InvoiceStatusDetailsComponent,
    InvoiceStatusDetailsDetailComponent,
    InvoiceStatusDetailsUpdateComponent,
    InvoiceStatusDetailsDeleteDialogComponent,
  ],
  entryComponents: [InvoiceStatusDetailsDeleteDialogComponent],
})
export class AppInvoiceStatusDetailsModule {}
