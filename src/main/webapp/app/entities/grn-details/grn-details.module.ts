import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { GRNDetailsComponent } from './grn-details.component';
import { GRNDetailsDetailComponent } from './grn-details-detail.component';
import { GRNDetailsUpdateComponent } from './grn-details-update.component';
import { GRNDetailsDeleteDialogComponent } from './grn-details-delete-dialog.component';
import { gRNDetailsRoute } from './grn-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(gRNDetailsRoute)],
  declarations: [GRNDetailsComponent, GRNDetailsDetailComponent, GRNDetailsUpdateComponent, GRNDetailsDeleteDialogComponent],
  entryComponents: [GRNDetailsDeleteDialogComponent],
})
export class AppGRNDetailsModule {}
