import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { CaseStatusDetailsComponent } from './case-status-details.component';
import { CaseStatusDetailsDetailComponent } from './case-status-details-detail.component';
import { CaseStatusDetailsUpdateComponent } from './case-status-details-update.component';
import { CaseStatusDetailsDeleteDialogComponent } from './case-status-details-delete-dialog.component';
import { caseStatusDetailsRoute } from './case-status-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(caseStatusDetailsRoute)],
  declarations: [
    CaseStatusDetailsComponent,
    CaseStatusDetailsDetailComponent,
    CaseStatusDetailsUpdateComponent,
    CaseStatusDetailsDeleteDialogComponent,
  ],
  entryComponents: [CaseStatusDetailsDeleteDialogComponent],
})
export class AppCaseStatusDetailsModule {}
