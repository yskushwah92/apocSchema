import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { OCRExtractionEngineInfoComponent } from './ocr-extraction-engine-info.component';
import { OCRExtractionEngineInfoDetailComponent } from './ocr-extraction-engine-info-detail.component';
import { OCRExtractionEngineInfoUpdateComponent } from './ocr-extraction-engine-info-update.component';
import { OCRExtractionEngineInfoDeleteDialogComponent } from './ocr-extraction-engine-info-delete-dialog.component';
import { oCRExtractionEngineInfoRoute } from './ocr-extraction-engine-info.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(oCRExtractionEngineInfoRoute)],
  declarations: [
    OCRExtractionEngineInfoComponent,
    OCRExtractionEngineInfoDetailComponent,
    OCRExtractionEngineInfoUpdateComponent,
    OCRExtractionEngineInfoDeleteDialogComponent,
  ],
  entryComponents: [OCRExtractionEngineInfoDeleteDialogComponent],
})
export class AppOCRExtractionEngineInfoModule {}
