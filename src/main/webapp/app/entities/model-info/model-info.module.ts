import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ModelInfoComponent } from './model-info.component';
import { ModelInfoDetailComponent } from './model-info-detail.component';
import { ModelInfoUpdateComponent } from './model-info-update.component';
import { ModelInfoDeleteDialogComponent } from './model-info-delete-dialog.component';
import { modelInfoRoute } from './model-info.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(modelInfoRoute)],
  declarations: [ModelInfoComponent, ModelInfoDetailComponent, ModelInfoUpdateComponent, ModelInfoDeleteDialogComponent],
  entryComponents: [ModelInfoDeleteDialogComponent],
})
export class AppModelInfoModule {}
