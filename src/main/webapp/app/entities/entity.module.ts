import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'organization',
        loadChildren: () => import('./organization/organization.module').then(m => m.AppOrganizationModule),
      },
      {
        path: 'ocr-extraction-engine-info',
        loadChildren: () =>
          import('./ocr-extraction-engine-info/ocr-extraction-engine-info.module').then(m => m.AppOCRExtractionEngineInfoModule),
      },
      {
        path: 'model-info',
        loadChildren: () => import('./model-info/model-info.module').then(m => m.AppModelInfoModule),
      },
      {
        path: 'file-info',
        loadChildren: () => import('./file-info/file-info.module').then(m => m.AppFileInfoModule),
      },
      {
        path: 'currency-exchange',
        loadChildren: () => import('./currency-exchange/currency-exchange.module').then(m => m.AppCurrencyExchangeModule),
      },
      {
        path: 'file-source-details',
        loadChildren: () => import('./file-source-details/file-source-details.module').then(m => m.AppFileSourceDetailsModule),
      },
      {
        path: 'purchase-order',
        loadChildren: () => import('./purchase-order/purchase-order.module').then(m => m.AppPurchaseOrderModule),
      },
      {
        path: 'purchase-order-header',
        loadChildren: () => import('./purchase-order-header/purchase-order-header.module').then(m => m.AppPurchaseOrderHeaderModule),
      },
      {
        path: 'purchase-order-line',
        loadChildren: () => import('./purchase-order-line/purchase-order-line.module').then(m => m.AppPurchaseOrderLineModule),
      },
      {
        path: 'vendor',
        loadChildren: () => import('./vendor/vendor.module').then(m => m.AppVendorModule),
      },
      {
        path: 'gl-account-details',
        loadChildren: () => import('./gl-account-details/gl-account-details.module').then(m => m.AppGLAccountDetailsModule),
      },
      {
        path: 'contact-details',
        loadChildren: () => import('./contact-details/contact-details.module').then(m => m.AppContactDetailsModule),
      },
      {
        path: 'vendor-payment-account-details',
        loadChildren: () =>
          import('./vendor-payment-account-details/vendor-payment-account-details.module').then(
            m => m.AppVendorPaymentAccountDetailsModule
          ),
      },
      {
        path: 'shipping-location',
        loadChildren: () => import('./shipping-location/shipping-location.module').then(m => m.AppShippingLocationModule),
      },
      {
        path: 'tax-code',
        loadChildren: () => import('./tax-code/tax-code.module').then(m => m.AppTaxCodeModule),
      },
      {
        path: 'invoice',
        loadChildren: () => import('./invoice/invoice.module').then(m => m.AppInvoiceModule),
      },
      {
        path: 'audit-log',
        loadChildren: () => import('./audit-log/audit-log.module').then(m => m.AppAuditLogModule),
      },
      {
        path: 'audit-log-details',
        loadChildren: () => import('./audit-log-details/audit-log-details.module').then(m => m.AppAuditLogDetailsModule),
      },
      {
        path: 'assignment',
        loadChildren: () => import('./assignment/assignment.module').then(m => m.AppAssignmentModule),
      },
      {
        path: 'invoice-status-details',
        loadChildren: () => import('./invoice-status-details/invoice-status-details.module').then(m => m.AppInvoiceStatusDetailsModule),
      },
      {
        path: 'invoice-header',
        loadChildren: () => import('./invoice-header/invoice-header.module').then(m => m.AppInvoiceHeaderModule),
      },
      {
        path: 'invoice-header-tax-details',
        loadChildren: () =>
          import('./invoice-header-tax-details/invoice-header-tax-details.module').then(m => m.AppInvoiceHeaderTaxDetailsModule),
      },
      {
        path: 'invoice-line-item',
        loadChildren: () => import('./invoice-line-item/invoice-line-item.module').then(m => m.AppInvoiceLineItemModule),
      },
      {
        path: 'grn-details',
        loadChildren: () => import('./grn-details/grn-details.module').then(m => m.AppGRNDetailsModule),
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.AppProductModule),
      },
      {
        path: 'ocr-raw-extraction',
        loadChildren: () => import('./ocr-raw-extraction/ocr-raw-extraction.module').then(m => m.AppOCRRawExtractionModule),
      },
      {
        path: 'case-status-details',
        loadChildren: () => import('./case-status-details/case-status-details.module').then(m => m.AppCaseStatusDetailsModule),
      },
      {
        path: 'communication-configuration',
        loadChildren: () =>
          import('./communication-configuration/communication-configuration.module').then(m => m.AppCommunicationConfigurationModule),
      },
      {
        path: 'mail-box',
        loadChildren: () => import('./mail-box/mail-box.module').then(m => m.AppMailBoxModule),
      },
      {
        path: 'notification-info',
        loadChildren: () => import('./notification-info/notification-info.module').then(m => m.AppNotificationInfoModule),
      },
      {
        path: 'payment-info',
        loadChildren: () => import('./payment-info/payment-info.module').then(m => m.AppPaymentInfoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AppEntityModule {}
