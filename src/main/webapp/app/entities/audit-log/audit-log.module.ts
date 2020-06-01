import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { AuditLogComponent } from './audit-log.component';
import { AuditLogDetailComponent } from './audit-log-detail.component';
import { AuditLogUpdateComponent } from './audit-log-update.component';
import { AuditLogDeleteDialogComponent } from './audit-log-delete-dialog.component';
import { auditLogRoute } from './audit-log.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(auditLogRoute)],
  declarations: [AuditLogComponent, AuditLogDetailComponent, AuditLogUpdateComponent, AuditLogDeleteDialogComponent],
  entryComponents: [AuditLogDeleteDialogComponent],
})
export class AppAuditLogModule {}
