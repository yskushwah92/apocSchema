import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { NotificationInfoComponent } from './notification-info.component';
import { NotificationInfoDetailComponent } from './notification-info-detail.component';
import { NotificationInfoUpdateComponent } from './notification-info-update.component';
import { NotificationInfoDeleteDialogComponent } from './notification-info-delete-dialog.component';
import { notificationInfoRoute } from './notification-info.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(notificationInfoRoute)],
  declarations: [
    NotificationInfoComponent,
    NotificationInfoDetailComponent,
    NotificationInfoUpdateComponent,
    NotificationInfoDeleteDialogComponent,
  ],
  entryComponents: [NotificationInfoDeleteDialogComponent],
})
export class AppNotificationInfoModule {}
