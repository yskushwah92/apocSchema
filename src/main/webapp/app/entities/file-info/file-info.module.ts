import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { FileInfoComponent } from './file-info.component';
import { FileInfoDetailComponent } from './file-info-detail.component';
import { FileInfoUpdateComponent } from './file-info-update.component';
import { FileInfoDeleteDialogComponent } from './file-info-delete-dialog.component';
import { fileInfoRoute } from './file-info.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(fileInfoRoute)],
  declarations: [FileInfoComponent, FileInfoDetailComponent, FileInfoUpdateComponent, FileInfoDeleteDialogComponent],
  entryComponents: [FileInfoDeleteDialogComponent],
})
export class AppFileInfoModule {}
