import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { MailBoxComponent } from './mail-box.component';
import { MailBoxDetailComponent } from './mail-box-detail.component';
import { MailBoxUpdateComponent } from './mail-box-update.component';
import { MailBoxDeleteDialogComponent } from './mail-box-delete-dialog.component';
import { mailBoxRoute } from './mail-box.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(mailBoxRoute)],
  declarations: [MailBoxComponent, MailBoxDetailComponent, MailBoxUpdateComponent, MailBoxDeleteDialogComponent],
  entryComponents: [MailBoxDeleteDialogComponent],
})
export class AppMailBoxModule {}
