import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { GLAccountDetailsComponent } from './gl-account-details.component';
import { GLAccountDetailsDetailComponent } from './gl-account-details-detail.component';
import { GLAccountDetailsUpdateComponent } from './gl-account-details-update.component';
import { GLAccountDetailsDeleteDialogComponent } from './gl-account-details-delete-dialog.component';
import { gLAccountDetailsRoute } from './gl-account-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(gLAccountDetailsRoute)],
  declarations: [
    GLAccountDetailsComponent,
    GLAccountDetailsDetailComponent,
    GLAccountDetailsUpdateComponent,
    GLAccountDetailsDeleteDialogComponent,
  ],
  entryComponents: [GLAccountDetailsDeleteDialogComponent],
})
export class AppGLAccountDetailsModule {}
