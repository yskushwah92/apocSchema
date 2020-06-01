import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { TaxCodeComponent } from './tax-code.component';
import { TaxCodeDetailComponent } from './tax-code-detail.component';
import { TaxCodeUpdateComponent } from './tax-code-update.component';
import { TaxCodeDeleteDialogComponent } from './tax-code-delete-dialog.component';
import { taxCodeRoute } from './tax-code.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(taxCodeRoute)],
  declarations: [TaxCodeComponent, TaxCodeDetailComponent, TaxCodeUpdateComponent, TaxCodeDeleteDialogComponent],
  entryComponents: [TaxCodeDeleteDialogComponent],
})
export class AppTaxCodeModule {}
