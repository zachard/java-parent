/*
 *  Copyright 2015-2017 zachard, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zachard.java.hello.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Java反射机制{@link Class}示例类
 * <pre>
 *     反射机制的主要作用: 
 *     (1)  在运行中分析类的能力
 *     (2)  在运行中查看对象, 例如: 编写{@link #toString()}方法供所有类使用
 *     (3)  实现通用的数组操作代码
 *     (4)  利用Method对象，类似于C++中的指针
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ClazzReflect {
	
	/**
	 * 无参数构造器,为{@link Class#newInstance()}方法创建对象时提供
	 */
	public ClazzReflect() {
		
	}
	
	/**
	 * 通过类对象获取{@link Object#getClass()}获取{@link Class}对象
	 * <pre>
	 *     使用场景: 非static方法
	 *     注意事项: (1) {@link #getClass()}方法获取到的是程序<b>运行时类型</b>的{@code Class}对象
	 *                  {@code //静态类型           运行时类型(实际类型)                  }
	 *                  {@code Number number = new Long("10");                        }
	 *                  {@code Class<? extends Number clazz = number.getClass();      }
	 *                  {@code System.err.println(clazz.getName()); //java.lang.Long  }
	 *                  
	 *              (2) {@link #getClass()}方法返回的实际类型为{@code Class<? extends |X|>}
	 *                  {@code |X|}为被{@link #getClass()}方法擦除的静态类型
	 *              (3) {@link #getClass()}为{@code final native}类型方法,所以不论是{@code this.getClass()}
	 *                  还是{@code super.getClass()}调用的都是{@link Object#getClass()}方法,而根据(1)可知,
	 *                  {@link Object#getClass()}得到的始终是运行时的类型,所以{@code super.getClass()}方法不会
	 *                  返回父类类型{@code Class}对象
	 * </pre>
	 * 
	 * @param object    实例对象
	 * @return          {@link Class}对象
	 */
	public Class<?> getClazzObject(Object object) {
		return object.getClass();
	}
	
	/**
	 * 通过T.class方式获取{@link Class}对象
	 * <pre>
	 *     使用场景: static方法
	 * </pre>
	 * 
	 * @param clazz   T.class获取到的{@link Class}对象
	 * @return        {@link Class}对象
	 */
	public Class<?> getClazzObject(Class<?> clazz) {
		return clazz;
	}
	
	/**
	 * 通过{@link Class#forName(String)}的方法获取一个{@link Class}对象
	 * <pre>
	 *     使用场景: 
	 *     (1) 类名保存在字符串中,并且在运行时可变
	 *     (2) 提供的参数必须是类或是接口
	 * </pre>
	 * 
	 * @param clazzName    完整限定类名(包名 + 类名)
	 * @return    {@link Class}对象
	 * @throws ClassNotFoundException   类不存在异常
	 */
	public Class<?> getClazzObject(String clazzName) throws ClassNotFoundException {
		return Class.forName(clazzName);
	}
	
	/**
	 * {@link Class}对象的用途, 通过{@link Class#getName()}获取一个对象的类名
	 * <pre>
	 *     注: 返回的是类的完整限定名
	 * </pre>
	 * 
	 * @param clazz    {@link Class}对象
	 * @return         {@link Class}对象类名
	 */
	public String getClazzName(Class<?> clazz) {
		return clazz.getName();
	}
	
	/**
	 * {@link Class}对象用途, 通过==判断两个类型对象是否相等
	 * <pre>
	 *     注: JVM为每个类型管理一个{@link Class}对象,因为子类类型的{@link Class}对象
	 *        不会等于父类的类型对象
	 * </pre>
	 * 
	 * @param src    源类型
	 * @param des    目标类型
	 * @return       类型是否相同
	 */
	public boolean judgeInstanceOf(Object src, Class<?> des) {
		return src.getClass() == des;
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#newInstance()}创建一个类型的实例对象
	 * <pre>
	 *     注意事项:
	 *     (1) {@link Class#newInstance()}调用的是默认(无参)构造器初始化新的对象,如果没有这个构造器,则会抛出异常
	 *     (2) 如果需要在构造器中传入参数,则需要使用{@link Constructor#newInstance(Object...)}方法
	 * </pre>
	 * 
	 * @param clazz    {@link Class}对象
	 * @return         与clazz类型相同的实例对象
	 * @throws InstantiationException    初始化异常
	 * @throws IllegalAccessException    参数异常
	 */
	public Object createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getSuperclass()}获取类的父类类型对象
	 * 
	 * @param clazz    类型对象
	 * @return         类型对应父类类型对象
	 */
	public Class<?> getSuperClazz(Class<?> clazz) {
		return clazz.getSuperclass();
	}
	
	/**
	 * {@link #getClass()}方法的错误用法示例
	 * 
	 * <p>
	 * {@link #getClass()}容易引起误解的地方: {@code super.getClass()}调用了父类的{@code getClass()}方法,
	 * 所以获取到的{@link Class}类型为父类类型
	 * </p>
	 * 
	 * @return    运行时的类型{@link Class}对象
	 * @see       #getClazzObject(Object)
	 * @see       #getSuperClazz(Class)
	 */
	public Class<?> cannotGetSuperClazzObject() {
		return super.getClass();
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getModifiers()}获取类型的修饰符
	 * <p>
	 *     {@link Class#getModifiers()}返回的是int值, 
	 *     需要通过{@link Modifier#toString()}方法转换为字符串修饰符
	 *     各个修饰符的int值详见API文档
	 * </p>
	 * 
	 * @param clazz   类型对象
	 * @return        类型对象的修饰符
	 */
	public String getClazzModifiers(Class<?> clazz) {
		return Modifier.toString(clazz.getModifiers());
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getConstructor(Class...)}
	 * 获取特定类型(调用{@link Class#getConstructor(Class...)}方法对象的类型)
	 * 的指定参数列表的公共构造器。
	 * <b>当参数列表匹配,但构造器为私有时,会抛出{@link NoSuchMethodException}异常</b>
	 * 
	 * <pre>
	 *     注: (1) 当参数长度为0时,抛出参数异常; 当参数长度为1时,返回此类型的无参数构造器(不存在则抛出
	 *             {@link NoSuchMethodException})异常; 当参数长度大于1时,返回第一个参数类型且参数为
	 *             其余类型的公有构造器,不存在则抛出{@link NoSuchMethodException}异常
	 *         (2) 示例代码: 
	 *             {@code // 只传递一个参数,获取其无参数构造器                                                    }
	 *             {@code Constructor<?> stringConstructor = clazzReflect.getClazzConstructor(String.class); }
	 *             {@code System.err.println(stringConstructor);                                             }
	 *             {@code // 输出结果: public java.lang.String()                                              }
	 *             
	 *             {@code // 传递多个参数                                                                                    }
	 *             {@code Constructor<?> doubleConstructor = clazzReflect.getClazzConstructor(Double.class, String.class); }
	 *             {@code System.err.println(doubleConstructor);                                                           }
	 *             {@code // 输出结果: public java.lang.Double(java.lang.String) throws java.lang.NumberFormatException     }
	 *             
	 *             {@code // User类中存在  private User(String name) 私有构造器                                               }
	 *             {@code Constructor<?> userConstructor = clazzReflect.getClazzConstructor(User.class, String.class);     }
	 *             {@code System.err.println(userConstructor);                                                             }
	 *             {@code // 抛出 NoSuchMethodException 异常                                                                }
	 * </pre>
	 * 
	 * @param clazz   参数数组
	 *                第一个参数表示需要获取构造器的类型, 其余参数表示构造器的参数(参数不能为空)
	 * @return        获取到的公有构造器
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public Constructor<?> getClazzConstructor(Class<?>... clazz) 
			throws NoSuchMethodException, SecurityException {
		
		if (clazz == null || clazz.length == 0) {
			throw new IllegalArgumentException("参数不允许为空");
		}
		
		if (clazz.length == 1) {
			//参数长度为1, 返回类型的无参数构造函数
			return clazz[0].getConstructor();
		}
		
		return clazz[0].getConstructor(Arrays.copyOfRange(clazz, 1, clazz.length));
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getDeclaredConstructor(Class...)}
	 * 获取特定类型(调用{@link Class#getDeclaredConstructor(Class...)}方法对象的类型)
	 * 的指定参数列表的构造器(公有和私有构造器都能获取)。
	 * 
	 * <pre>
	 *     注: (1) 当参数长度为0时,抛出参数异常; 当参数长度为1时,返回此类型的无参数构造器(不存在则抛出
	 *             {@link NoSuchMethodException})异常; 当参数长度大于1时,返回第一个参数类型且参数为
	 *             其余类型的构造器,不存在则抛出{@link NoSuchMethodException}异常
	 *         (2) 示例代码: 
	 *             {@code // 只传递一个参数,获取其无参数构造器                                                    }
	 *             {@code Constructor<?> stringConstructor = clazzReflect.getClazzConstructor(String.class); }
	 *             {@code System.err.println(stringConstructor);                                             }
	 *             {@code // 输出结果: public java.lang.String()                                              }
	 *             
	 *             {@code // 传递多个参数                                                                                    }
	 *             {@code Constructor<?> doubleConstructor = clazzReflect.getClazzConstructor(Double.class, String.class); }
	 *             {@code System.err.println(doubleConstructor);                                                           }
	 *             {@code // 输出结果: public java.lang.Double(java.lang.String) throws java.lang.NumberFormatException     }
	 *             
	 *             {@code // User类中存在  private User(String name) 私有构造器                                               }
	 *             {@code Constructor<?> userConstructor = clazzReflect.getClazzConstructor(User.class, String.class);     }
	 *             {@code System.err.println(userConstructor);                                                             }
	 *             {@code // 输出结果: private User(java.lang.String)                                                       }
	 * </pre>
	 * 
	 * @param clazz   参数数组
	 *                第一个参数表示需要获取构造器的类型, 其余参数表示构造器的参数(参数不能为空)
	 * @return        获取到的构造器
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public Constructor<?> getClazzDeclaredConstructor(Class<?>... clazz) 
			throws NoSuchMethodException, SecurityException {
		
		if (clazz == null || clazz.length == 0) {
			throw new IllegalArgumentException("参数不允许为空");
		}
		
		if (clazz.length == 1) {
			return clazz[0].getDeclaredConstructor();
		}
		
		return clazz[0].getDeclaredConstructor(Arrays.copyOfRange(clazz, 1, clazz.length));
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getConstructors()}获取指定类型(调用函数类型)的所有公有构造器
	 * 
	 * <pre>
	 *     注: (1) 只能获取指定类型的公有构造器,返回构造器数组
	 *         (2) 示例代码:
	 *             {@code  // String类型的公有构造器                                                                  }
	 *             {@code  Constructor<?>[] stringConstructors = clazzReflect.getClazzConstructors(String.class);  }
	 *             {@code  System.err.println("String类型的公有构造器个数为: " + stringConstructors.length);           }
	 *             {@code  // 输出结果: String类型的公有构造器个数为: 15                                                }
	 *             
	 *             {@code  // 接口测试                                                                                   }
	 *             {@code  Constructor<?>[] doubleConstructors = clazzReflect.getClazzConstructors(Comparable.class);   }
	 *             {@code  System.err.println("Comparable类型的公有构造器为: " + doubleConstructors.length);               }
	 *             {@code  // 输出结果: Comparable类型的公有构造器为: 0                                                     }
	 *             
	 *             {@code  // User类含有 private User(), private User(String name), public User(String name, Integer age) 三个构造器  }
	 *             {@code  Constructor<?>[] constructors = clazzReflect.getClazzConstructors(User.class);                           }
	 *             {@code  System.err.println("User类型的公有构造器个数为: " + constructors.length);                                   }
	 *             {@code  // 输出结果: User类型的公有构造器个数为: 1                                                                    }
	 * </pre>
	 * 
	 * @param clazz    需要获取的所有公有构造器的类型
	 * @return         指定类型的所有公有构造器
	 */
	public Constructor<?>[] getClazzConstructors(Class<?> clazz) {
		return clazz.getConstructors();
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getDeclaredConstructors()}获取指定类型(调用函数类型)的所有构造器
	 * 
	 * <pre>
	 *     注: (1) 示例代码:
	 *             {@code  // String类型的所有构造器                                                                    }
	 *             {@code  Constructor<?>[] stringConstructors = clazzReflect.getDeclaredConstructors(String.class);  }
	 *             {@code  System.err.println("String类型的构造器个数为: " + stringConstructors.length);                 }
	 *             {@code  // 输出结果: String类型的公有构造器个数为: 16                                                   }
	 *             
	 *             {@code  // 接口测试                                                                                      }
	 *             {@code  Constructor<?>[] doubleConstructors = clazzReflect.getDeclaredConstructors(Comparable.class);   }
	 *             {@code  System.err.println("Comparable类型的构造器为: " + doubleConstructors.length);                      }
	 *             {@code  // 输出结果: Comparable类型的公有构造器为: 0                                                         }
	 *             
	 *             {@code  // User类含有 private User(), private User(String name), public User(String name, Integer age) 三个构造器  }
	 *             {@code  Constructor<?>[] constructors = clazzReflect.getDeclaredConstructors(User.class);                       }
	 *             {@code  System.err.println("User类型的构造器个数为: " + constructors.length);                                      }
	 *             {@code  // 输出结果: User类型的构造器个数为: 3                                                                      }
	 * </pre>
	 * 
	 * @param clazz    需要获取的所有构造器的类型
	 * @return         指定类型的所有构造器
	 */
	public Constructor<?>[] getClazzDeclaredConstructors(Class<?> clazz) {
		return clazz.getDeclaredConstructors();
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getField(String)}方法获取指定类型(调用类型)的特定名称的公有成员变量
	 * 
	 * <pre>
	 *     注: (1) {@link Class#getField(String)}只能获取公有的成员变量,对于私有及不存在的成员变量会抛出{@link NoSuchFieldException}异常
	 *         (2) 成员变量搜索顺序: 
	 *             1. 如果当前类型中存在名称相同的成员变量,则返回当前类型中的成员变量
	 *             2. 如果第 1 步不满足,则按照当前类型直接实现接口的顺序查找接口中名称相同的成员变量,如果存在则返回
	 *             3. 如果 1, 2 步都不满足, 则查找此类型的父类型中是否存在名称相同的成员变量,如果存在则返回。若不存在或者
	 *                此类型没有父类型则抛出{@link NoSuchFieldException}异常
	 *         (3) 示例代码: 
	 *             {@code  // 当只有成员变量只在本类中存在时,则获取本类中的成员变量                                   }
	 *             {@code  String fieldName = "existClazz";                                                  }
	 *             {@code  Field clazzField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);  }
	 *             {@code  System.err.println(clazzField);                                                   }
	 *             {@code  // 输出结果(简写): public Double HomeServiceImpl.existClazz                         }
	 *             
	 *             {@code  // 当成员变量在本类和实现接口中不存在,但在继承类中存在时,获取继承类的成员变量                   }
	 *             {@code  fieldName = "existExtends";                                                         }
	 *             {@code  Field extendsField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);  }
	 *             {@code  System.err.println(extendsField);                                                   }
	 *             {@code  // 输出结果(简写): public Integer AbstractService.existExtends                        }
	 *             
	 *             {@code  // 当成员变量在本类和继承类中不存在,但在实现接口中存在时,获取实现接口中的成员变量               }
	 *             {@code  fieldName = "existImpl";                                                           }
	 *             {@code  Field implField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);    }
	 *             {@code  System.err.println(implField);                                                     }
	 *             {@code  // 输出结果(简写): public static final String HomeService.existImpl                  }
	 *             
	 *             {@code  // 当成员变量在本类中不存在,但在继承类及实现接口中存在时,获取实现接口中的成员变量                       }
	 *             {@code  fieldName = "existExtendsAndImpl";                                                         }
	 *             {@code  Field extendsAndImplField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);  }
	 *             {@code  System.err.println(extendsAndImplField);                                                   }
	 *             {@code  // 输出结果(简写): public static final String HomeService.existExtendsAndImpl                }
	 *             
	 *             {@code  // 当成员变量在本类,继承类及实现接口中都存在时,则获取本类中的成员变量                                 }
	 *             {@code  fieldName = "existAll";                                                                    }
	 *             {@code  Field allField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);             }
	 *             {@code  System.err.println(allField);                                                              }
	 *             {@code  // 输出结果(简写): public Double HomeServiceImpl.existAll                                    }
	 *             
	 *             {@code  // 获取不存在的成员变量(私有也属于不存在)时,抛出NoSuchFieldException                              }
	 *             {@code  fieldName = "privateField";                                                                }
	 *             {@code  Field notExistField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);        }
	 *             {@code  System.err.println("成员变量是否存在: " + (notExistField != null));                           }
	 *             {@code  // 抛出NoSuchFieldException异常                                                              }
	 * </pre>
	 * 
	 * @param clazz    需要获取属性的类型
	 * @param name     属性名称
	 * @return         属性对象
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public Field getClazzField(Class<?> clazz, String name) 
			throws NoSuchFieldException, SecurityException {
		return clazz.getField(name);
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#getDeclaredField(String)}方法获取指定类型(调用类型)
	 * 的特定名称的成员变量
	 * 
	 * <pre>
	 *     注: (1) {@link Class#getDeclaredField(String)}可以获取类型的成员变量,对于不存在的成员变量会抛出{@link NoSuchFieldException}异常
	 *         (2) 与{@link Class#getField(String)}方法不同的是,{@link Class#getDeclaredField(String)}只会搜索本类中的成员变量
	 *             而不会到继承类或是实现接口中查找(公有成员变量也是如此)
	 *         (3) 示例代码: 
	 *             {@code  // 当公有成员变量在本类中存在时,则获取对应的成员变量                                      }
	 *             {@code  String fieldName = "existClazz";                                                  }
	 *             {@code  Field clazzField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);  }
	 *             {@code  System.err.println(clazzField);                                                   }
	 *             {@code  // 输出结果(简写): public Double HomeServiceImpl.existClazz                         }
	 *             
	 *             {@code  // 当私有成员变量在本类中存在时,则获取对应的成员变量                                                    }
	 *             {@code  fieldName = "privateField";                                                                     }
	 *             {@code  Field privateClazzField = clazzReflect.getClazzDeclaredField(HomeServiceImpl.class, fieldName); }
	 *             {@code  System.err.println(privateClazzField);                                                          }
	 *             {@code  // 输出结果(简写): private Double HomeServiceImpl.privateField                                    }
	 *             
	 *             {@code  // 当成员变量在本类中不存在,但在继承类及实现接口中存在时,抛出NoSuchFieldException异常                }
	 *             {@code  fieldName = "existExtendsAndImpl";                                                         }
	 *             {@code  Field extendsAndImplField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);  }
	 *             {@code  System.err.println(extendsAndImplField);                                                   }
	 *             {@code  // 抛出NoSuchFieldException异常                                                             }
	 *             
	 *             {@code  // 获取不存在的成员变量(私有也属于不存在)时,抛出NoSuchFieldException                              }
	 *             {@code  fieldName = "notExist";                                                                }
	 *             {@code  Field notExistField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);        }
	 *             {@code  System.err.println("成员变量是否存在: " + (notExistField != null));                           }
	 *             {@code  // 抛出NoSuchFieldException异常                                                              }
	 * </pre>
	 * 
	 * @param clazz    需要获取属性的类型
	 * @param name     属性名称
	 * @return         属性对象
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public Field getClazzDeclaredField(Class<?> clazz, String name) 
			throws NoSuchFieldException, SecurityException {
		return clazz.getDeclaredField(name);
	}

}
