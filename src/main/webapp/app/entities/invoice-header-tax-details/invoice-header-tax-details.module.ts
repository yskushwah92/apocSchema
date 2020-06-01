import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { InvoiceHeaderTaxDetailsComponent } from './invoice-header-tax-details.component';
import { InvoiceHeaderTaxDetailsDetailComponent } from './invoice-header-tax-details-detail.component';
import { InvoiceHeaderTaxDetailsUpdateComponent } from './invoice-header-tax-details-update.component';
import { InvoiceHeaderTaxDetailsDeleteDialogComponent } from './invoice-header-tax-details-delete-dialog.component';
import { invoiceHeaderTaxDetailsRoute } from './invoice-header-tax-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(invoiceHeaderTaxDetailsRoute)],
  declarations: [
    InvoiceHeaderTaxDetailsComponent,
    InvoiceHeaderTaxDetailsDetailComponent,
    InvoiceHeaderTaxDetailsUpdateComponent,
    InvoiceHeaderTaxDetailsDeleteDialogComponent,
  ],
  entryComponents: [InvoiceHeaderTaxDetailsDeleteDialogComponent],
})
export class AppInvoiceHeaderTaxDetailsModule {}
