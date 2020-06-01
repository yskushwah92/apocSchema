import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';

@Component({
  selector: 'jhi-gl-account-details-detail',
  templateUrl: './gl-account-details-detail.component.html',
})
export class GLAccountDetailsDetailComponent implements OnInit {
  gLAccountDetails: IGLAccountDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gLAccountDetails }) => (this.gLAccountDetails = gLAccountDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
