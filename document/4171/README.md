```html
<select class="form-control" id="migrateType" (change)="template = $event.target.value">
  <option value="1">batch update</option>
</select>
```

> template 是ts组件中的变量，可以把选中的值直接赋值给template
