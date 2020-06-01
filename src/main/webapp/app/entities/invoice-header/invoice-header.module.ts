import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { InvoiceHeaderComponent } from './invoice-header.component';
import { InvoiceHeaderDetailComponent } from './invoice-header-detail.component';
import { InvoiceHeaderUpdateComponent } from './invoice-header-update.component';
import { InvoiceHeaderDeleteDialogComponent } from './invoice-header-delete-dialog.component';
import { invoiceHeaderRoute } from './invoice-header.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(invoiceHeaderRoute)],
  declarations: [InvoiceHeaderComponent, InvoiceHeaderDetailComponent, InvoiceHeaderUpdateComponent, InvoiceHeaderDeleteDialogComponent],
  entryComponents: [InvoiceHeaderDeleteDialogComponent],
})
export class AppInvoiceHeaderModule {}
