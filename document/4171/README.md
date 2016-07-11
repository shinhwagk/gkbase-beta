例1
```html
<select (change)="template = $event.target.value">
  <option value="1">batch update</option>
</select>
```
> template 是ts组件中的变量，可以把选中的值直接赋值给template

例2
```html
<select (change)="setValue($event.target.value)">
  <option value="1">batch update</option>
</select>
>setValue是ts组建中的一个方法，接受一个值```

