import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetallpublisherComponent } from './getallpublisher.component';

describe('GetallpublisherComponent', () => {
  let component: GetallpublisherComponent;
  let fixture: ComponentFixture<GetallpublisherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetallpublisherComponent]
    });
    fixture = TestBed.createComponent(GetallpublisherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
