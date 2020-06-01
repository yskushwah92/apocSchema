import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { CommunicationConfigurationComponent } from 'app/entities/communication-configuration/communication-configuration.component';
import { CommunicationConfigurationService } from 'app/entities/communication-configuration/communication-configuration.service';
import { CommunicationConfiguration } from 'app/shared/model/communication-configuration.model';

describe('Component Tests', () => {
  describe('CommunicationConfiguration Management Component', () => {
    let comp: CommunicationConfigurationComponent;
    let fixture: ComponentFixture<CommunicationConfigurationComponent>;
    let service: CommunicationConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CommunicationConfigurationComponent],
      })
        .overrideTemplate(CommunicationConfigurationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommunicationConfigurationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommunicationConfigurationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CommunicationConfiguration(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.communicationConfigurations && comp.communicationConfigurations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
