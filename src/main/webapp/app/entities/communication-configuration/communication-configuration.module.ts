import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { CommunicationConfigurationComponent } from './communication-configuration.component';
import { CommunicationConfigurationDetailComponent } from './communication-configuration-detail.component';
import { CommunicationConfigurationUpdateComponent } from './communication-configuration-update.component';
import { CommunicationConfigurationDeleteDialogComponent } from './communication-configuration-delete-dialog.component';
import { communicationConfigurationRoute } from './communication-configuration.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(communicationConfigurationRoute)],
  declarations: [
    CommunicationConfigurationComponent,
    CommunicationConfigurationDetailComponent,
    CommunicationConfigurationUpdateComponent,
    CommunicationConfigurationDeleteDialogComponent,
  ],
  entryComponents: [CommunicationConfigurationDeleteDialogComponent],
})
export class AppCommunicationConfigurationModule {}
