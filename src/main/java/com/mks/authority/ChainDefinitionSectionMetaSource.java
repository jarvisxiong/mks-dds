package com.mks.authority;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;

import com.mks.authority.dao.ResourceDao;
import com.mks.authority.entity.Resource;

 
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

    private ResourceDao resourceDao;
    private String filterChainDefinitions;
    
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Override
    public Section getObject() throws Exception {
        // 获取所有Resource
        List<Resource> list = resourceDao.queryAll();
        Ini ini = new Ini();
        // 加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        // 循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        // 里面的键就是链接URL,值就是存在什么条件才能访问该链接
        for (Iterator<Resource> it = list.iterator(); it.hasNext();) {
            Resource resource = it.next();
            // 如果不为空值添加到section中
            if (StringUtils.isNotEmpty(resource.getUrl()) && StringUtils.isNotEmpty(resource.getIdentifying())) {
                section.put(resource.getUrl(), MessageFormat.format(PREMISSION_STRING, resource.getIdentifying()));
            }
        }
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
}
