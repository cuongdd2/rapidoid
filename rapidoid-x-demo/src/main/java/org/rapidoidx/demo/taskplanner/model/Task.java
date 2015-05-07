package org.rapidoidx.demo.taskplanner.model;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.DbEntity;
import org.rapidoid.annotation.Display;
import org.rapidoid.annotation.Optional;
import org.rapidoid.annotation.Programmatic;
import org.rapidoid.annotation.Scaffold;
import org.rapidoid.annotation.Since;
import org.rapidoid.security.annotation.CanChange;
import org.rapidoid.security.annotation.CanRead;
import org.rapidoid.util.CommonRoles;
import org.rapidoidx.db.XDB;
import org.rapidoidx.db.DbList;
import org.rapidoidx.db.DbRef;
import org.rapidoidx.db.DbSet;

/*
 * #%L
 * rapidoid-demo
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

@Scaffold
@SuppressWarnings("serial")
// @CanRead(CommonRoles.LOGGED_IN)
// @CanChange({ CommonRoles.OWNER })
// @CanInsert(CommonRoles.LOGGED_IN)
// @CanDelete({ CommonRoles.OWNER, CommonRoles.ADMIN })
@DbEntity
@Authors("Nikolche Mihajlovski")
@Since("2.0.0")
public class Task extends Entity {

	@Display
	@CanChange({ MODERATOR, OWNER })
	public String title;

	@Display
	@CanChange({ MODERATOR, OWNER, SHARED_WITH })
	public Priority priority = Priority.MEDIUM;

	@Optional
	@CanChange({ MODERATOR, OWNER, SHARED_WITH })
	public String description;

	public int rating;

	@Programmatic
	public final DbRef<User> owner = XDB.ref(this, "^owns");

	@CanRead({ CommonRoles.OWNER })
	public final DbSet<User> sharedWith = XDB.set(this, "sharedWith");

	@Programmatic
	@CanRead({ CommonRoles.OWNER, CommonRoles.SHARED_WITH })
	public final DbList<Comment> comments = XDB.list(this, "has");

	@Programmatic
	public final DbSet<User> likedBy = XDB.set(this, "^likes");

}
