import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITaxCode } from 'app/shared/model/tax-code.model';

@Component({
  selector: 'jhi-tax-code-detail',
  templateUrl: './tax-code-detail.component.html',
})
export class TaxCodeDetailComponent implements OnInit {
  taxCode: ITaxCode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taxCode }) => (this.taxCode = taxCode));
  }

  previousState(): void {
    window.history.back();
  }
}
