import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetbypublisheridComponent } from './getbypublisherid.component';

describe('GetbypublisheridComponent', () => {
  let component: GetbypublisheridComponent;
  let fixture: ComponentFixture<GetbypublisheridComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetbypublisheridComponent]
    });
    fixture = TestBed.createComponent(GetbypublisheridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
