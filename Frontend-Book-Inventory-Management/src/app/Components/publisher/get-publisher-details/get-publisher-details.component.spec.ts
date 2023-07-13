import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPublisherDetailsComponent } from './get-publisher-details.component';

describe('GetPublisherDetailsComponent', () => {
  let component: GetPublisherDetailsComponent;
  let fixture: ComponentFixture<GetPublisherDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetPublisherDetailsComponent]
    });
    fixture = TestBed.createComponent(GetPublisherDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
