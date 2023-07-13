import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetbycategoryComponent } from './getbycategory.component';

describe('GetbycategoryComponent', () => {
  let component: GetbycategoryComponent;
  let fixture: ComponentFixture<GetbycategoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetbycategoryComponent]
    });
    fixture = TestBed.createComponent(GetbycategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
