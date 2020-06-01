import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { FileSourceDetailsComponent } from './file-source-details.component';
import { FileSourceDetailsDetailComponent } from './file-source-details-detail.component';
import { FileSourceDetailsUpdateComponent } from './file-source-details-update.component';
import { FileSourceDetailsDeleteDialogComponent } from './file-source-details-delete-dialog.component';
import { fileSourceDetailsRoute } from './file-source-details.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(fileSourceDetailsRoute)],
  declarations: [
    FileSourceDetailsComponent,
    FileSourceDetailsDetailComponent,
    FileSourceDetailsUpdateComponent,
    FileSourceDetailsDeleteDialogComponent,
  ],
  entryComponents: [FileSourceDetailsDeleteDialogComponent],
})
export class AppFileSourceDetailsModule {}
