import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { AuditLogDetailsComponent } from './audit-log-details.component';
import { AuditLogDetailsDetailComponent } from './audit-log-details-detail.component';
import { AuditLogDetailsUpdateComponent } from './audit-log-details-update.component';
import { AuditLogDetailsDeleteDialogComponent } from './audit-log-details-delete-dialog.component';
import { auditLogDetailsRoute } from './audit-log-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(auditLogDetailsRoute)],
  declarations: [
    AuditLogDetailsComponent,
    AuditLogDetailsDetailComponent,
    AuditLogDetailsUpdateComponent,
    AuditLogDetailsDeleteDialogComponent,
  ],
  entryComponents: [AuditLogDetailsDeleteDialogComponent],
})
export class AppAuditLogDetailsModule {}
