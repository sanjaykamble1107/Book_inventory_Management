import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddpublisherComponent } from './addpublisher.component';

describe('AddpublisherComponent', () => {
  let component: AddpublisherComponent;
  let fixture: ComponentFixture<AddpublisherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddpublisherComponent]
    });
    fixture = TestBed.createComponent(AddpublisherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
