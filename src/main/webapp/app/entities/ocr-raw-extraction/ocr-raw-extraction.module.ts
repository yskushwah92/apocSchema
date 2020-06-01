import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { OCRRawExtractionComponent } from './ocr-raw-extraction.component';
import { OCRRawExtractionDetailComponent } from './ocr-raw-extraction-detail.component';
import { OCRRawExtractionUpdateComponent } from './ocr-raw-extraction-update.component';
import { OCRRawExtractionDeleteDialogComponent } from './ocr-raw-extraction-delete-dialog.component';
import { oCRRawExtractionRoute } from './ocr-raw-extraction.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(oCRRawExtractionRoute)],
  declarations: [
    OCRRawExtractionComponent,
    OCRRawExtractionDetailComponent,
    OCRRawExtractionUpdateComponent,
    OCRRawExtractionDeleteDialogComponent,
  ],
  entryComponents: [OCRRawExtractionDeleteDialogComponent],
})
export class AppOCRRawExtractionModule {}
